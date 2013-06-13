package com.vaadin.training.bugrap.domain.repository;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.vaadin.training.bugrap.domain.entity.*;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class ReportRepository extends AbstractRepository<Report> {

    private boolean whereAdded;

    @Override
    protected Class<Report> getEntityClass() {
        return Report.class;
    }

    public List<Report> findReports(ReportQuery reportQuery) {
        User assignee = reportQuery.getAssignee();
        ProjectVersion version = reportQuery.getVersion();
        ReportStatus status = reportQuery.getStatus();
        List<ReportResolution> resolutions = reportQuery.getResolutions();
        String searchTerm = reportQuery.getSearchTerm();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select r from Report r ");

        List<String> criteria = Lists.newLinkedList();

        if (assignee != null) {
            criteria.add("r.assigned.id = :assigneeId");
        }

        if (version != null) {
            criteria.add("r.projectVersion.id = :versionId");
        }

        if (status != null) {
            criteria.add("r.status = :status");
        }

        if (!resolutions.isEmpty()) {
            criteria.add("r.resolution in :resolutions");
        }


        if (!criteria.isEmpty()) {
            stringBuilder.append("where ");
            whereAdded = true;
            stringBuilder.append(Joiner.on(" and ").join(criteria));
        }

        addSearchCriteria(searchTerm, stringBuilder, whereAdded);

        String queryString = stringBuilder.toString();
        System.out.println(queryString);

        TypedQuery<Report> query = em.createQuery(queryString, Report.class);


        if (assignee != null) {
            query.setParameter("assigneeId", assignee.getId());
        }
        if (version != null) {
            query.setParameter("versionId", version.getId());
        }
        if (status != null) {
            query.setParameter("status", status);
        }
        if (!resolutions.isEmpty()) {
            query.setParameter("resolutions", resolutions);
        }
        if (searchTerm != null && !searchTerm.isEmpty()) {
            String searchWildcard = "%" + searchTerm.toUpperCase() + "%";
            query.setParameter("summary", searchWildcard);
            query.setParameter("description", searchWildcard);
        }

        List<Report> resultList = query.getResultList();
        return resultList;
    }

    private void addSearchCriteria(String searchTerm, StringBuilder stringBuilder, boolean whereAdded) {
        List<String> criteria = Lists.newLinkedList();

        if (searchTerm != null && !searchTerm.isEmpty()) {
            criteria.add("upper(r.summary) like :summary");
            criteria.add("upper(r.description) like :description");
        }

        if (!criteria.isEmpty()) {
            if (whereAdded) {
                stringBuilder.append("and (");
            } else {
                stringBuilder.append("where (");
            }

            stringBuilder.append(Joiner.on(" or ").join(criteria));
            stringBuilder.append(")");
        }
    }
}
