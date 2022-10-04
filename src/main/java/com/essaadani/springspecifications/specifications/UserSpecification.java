package com.essaadani.springspecifications.specifications;

import com.essaadani.springspecifications.criterieas.SearchCriteria;
import com.essaadani.springspecifications.entities.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class UserSpecification implements Specification<AppUser> {

    private SearchCriteria searchCriteria;

    @Override
    public Predicate toPredicate(Root<AppUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if(searchCriteria.getOperation().equalsIgnoreCase(">")){
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get(searchCriteria.getKey()), searchCriteria.getValue().toString());
        }else if(searchCriteria.getOperation().equalsIgnoreCase("<")){
            return criteriaBuilder.lessThanOrEqualTo(
                    root.get(searchCriteria.getKey()), searchCriteria.getValue().toString());

        }else if(searchCriteria.getOperation().equals(":")){
            // for string values
            if(root.get(searchCriteria.getKey()).getJavaType() == String.class){
                return criteriaBuilder.like(root.get(searchCriteria.getOperation()), "%"+searchCriteria.getValue()+"%");

            }else{
                return criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());
            }
        }
        return null;
    }
}
