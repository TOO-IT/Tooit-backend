package com.kr.tooit.domain.vote.service;

import com.kr.tooit.domain.vote.domain.Vote;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class VoteSpecification {

    public static Specification<Vote> equalsDeleteStatus(String deleteStatus) {
        return new Specification<Vote>() {
            @Override
            public Predicate toPredicate(Root<Vote> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("deleteStatus"), deleteStatus);
            }
        };
    }

    public static Specification<Vote> equalsDeadlineStatus(String deadlineStatus) {
        return new Specification<Vote>() {
            @Override
            public Predicate toPredicate(Root<Vote> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("deadlineStatus"), deadlineStatus);
            }
        };
    }


    public static Specification<Vote> equalsShareTarget(String shareTarget) {
        return new Specification<Vote>() {
            @Override
            public Predicate toPredicate(Root<Vote> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("shareTarget"), shareTarget);
            }
        };
    }

}
