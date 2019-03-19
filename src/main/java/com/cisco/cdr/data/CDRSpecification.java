package com.cisco.cdr.data;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.cisco.cdr.api.payload.Cdr;

public class CDRSpecification {

	private Cdr messageFilter;

	public CDRSpecification(Cdr messageFilter) {
		this.messageFilter = messageFilter;
	}

	public static Specification<Cdr> contain(Cdr messageFilter) {
		return new Specification<Cdr>() {
			@Override
            public Predicate toPredicate(Root<Cdr> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                final Collection<Predicate> predicates = new ArrayList<>();
                
                if (messageFilter.getCdrId() != null) {
                    final Predicate idPredicate = cb.equal(root.get("id"), messageFilter.getCdrId());
                    predicates.add(idPredicate);
                }
                
                if (!StringUtils.isEmpty(messageFilter.getOperatorId())) {
                    final Predicate sourcePredicate = cb.like(root.get("operatorId"), "%"+messageFilter.getOperatorId()+"%");
                    predicates.add(sourcePredicate);
                }
                
                if (!StringUtils.isEmpty(messageFilter.getAcctId())) {
                    final Predicate typePredicate = cb.like(root.get("acctId"), "%"+messageFilter.getAcctId()+"%");
                    predicates.add(typePredicate);
                }
                
                if (!StringUtils.isEmpty(messageFilter.getStatus())) {
                    final Predicate descPredicate = cb.like(root.get("status"), "%"+messageFilter.getStatus()+"%");
                    predicates.add(descPredicate);
                }
                if (messageFilter.getDateAdded() != null) {
                    final Predicate dateAddedPredicate = cb.equal(root.get("dateAdded"), messageFilter.getDateAdded());
                    predicates.add(dateAddedPredicate);
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
		};
	}
}
