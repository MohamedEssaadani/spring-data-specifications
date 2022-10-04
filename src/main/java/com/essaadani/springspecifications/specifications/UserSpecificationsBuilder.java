package com.essaadani.springspecifications.specifications;

import com.essaadani.springspecifications.criterieas.SearchCriteria;
import com.essaadani.springspecifications.entities.AppUser;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;

public class UserSpecificationsBuilder {
        private final List<SearchCriteria> params;

        public UserSpecificationsBuilder() {
            params = new ArrayList<SearchCriteria>();
        }

        public UserSpecificationsBuilder with(String key, String operation, Object value) {
            params.add(new SearchCriteria(key, operation, value));
            return this;
        }

        public Specification<AppUser> build() {
            if (params.size() == 0) {
                return null;
            }

            List<Specification> specs = params.stream()
                    .map(UserSpecification::new)
                    .collect(Collectors.toList());

            Specification result = specs.get(0);

            for (int i = 1; i < params.size(); i++) {
                result = where(result).and(specs.get(i));
            }
            return result;
        }

}
