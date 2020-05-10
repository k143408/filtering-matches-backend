package com.code.exercise.matches.repository.specification;

import com.code.exercise.matches.domain.User;
import com.code.exercise.matches.dto.FilterCriteria;
import org.hibernate.spatial.SpatialFunction;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.Optional;

public class UserSpecifications {
    public static Specification<User> findByFilterCriteria(FilterCriteria fc) {

        return (r, cq, cb) -> {
            Predicate favourite = cb.equal(r.get("favourite"), fc.getFavourite());
            Predicate hasPhoto = Optional.of(fc.getHasPhoto()).filter(f -> f == true).map(p -> cb.isNotNull(r.get("mainPhoto"))).orElseGet(() -> cb.isNull(r.get("mainPhoto")));
            Predicate inContact = Optional.of(fc.getInContact()).filter(f -> f == true).map(c -> cb.notEqual(r.get("contactsExchanged"), 0)).orElseGet(() -> cb.equal(r.get("contactsExchanged"), 0));
            Predicate compatibilityScore = cb.between(r.get("compatibilityScore"), fc.getMinCompatibilityScore(), fc.getMaxCompatibilityScore());
            Predicate age = cb.between(r.get("age"), fc.getMinAge(), fc.getMaxAge());
            Predicate heightInCm = cb.between(r.get("heightInCm"), fc.getMinHeight(), fc.getMaxHeight());
            // Getting distance
            Expression<Double> distance = cb.function(SpatialFunction.distance.toString(), double.class,
                    r.get("city").get("location"), cb.literal(fc.getCenterPoint()));
            Predicate distancePredicate = Optional.of(fc.getDistanceStatus())
                    .filter(f -> f == true)
                    .map(s -> cb.lessThan(distance, fc.getDistance())).orElseGet(() -> cb.greaterThan(distance, fc.getDistance()));
            return cb.and(favourite, hasPhoto, inContact, compatibilityScore, age, heightInCm, distancePredicate);
        };

    }
}
