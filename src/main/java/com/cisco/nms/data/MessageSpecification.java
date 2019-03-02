package com.cisco.nms.data;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.cisco.nms.api.payload.Message;

public class MessageSpecification {

	private Message messageFilter;

	public MessageSpecification(Message messageFilter) {
		this.messageFilter = messageFilter;
	}

	public static Specification<Message> contain(Message messageFilter) {
		return new Specification<Message>() {
			@Override
            public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                final Collection<Predicate> predicates = new ArrayList<>();
                
                if (messageFilter.getId() != null) {
                    final Predicate idPredicate = cb.equal(root.get("id"), messageFilter.getId());
                    predicates.add(idPredicate);
                }
                
                if (!StringUtils.isEmpty(messageFilter.getSource())) {
                    final Predicate sourcePredicate = cb.like(root.get("source"), "%"+messageFilter.getSource()+"%");
                    predicates.add(sourcePredicate);
                }
                
                if (!StringUtils.isEmpty(messageFilter.getType())) {
                    final Predicate typePredicate = cb.like(root.get("type"), "%"+messageFilter.getType()+"%");
                    predicates.add(typePredicate);
                }
                
                if (!StringUtils.isEmpty(messageFilter.getDescription())) {
                    final Predicate descPredicate = cb.like(root.get("description"), "%"+messageFilter.getDescription()+"%");
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
