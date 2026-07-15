package LibraryManagementSystem.factory;

import LibraryManagementSystem.entity.Member;
import LibraryManagementSystem.enums.MemberType;

import java.time.LocalDate;

public final class MemberFactory {

    private MemberFactory() {
    }

    public static Member create(String personId,
                                String name, String phone,
                                MemberType memberType,
                                LocalDate membershipExpiryDate) {
        return new Member(personId, name, phone, memberType, membershipExpiryDate);
    }
}
