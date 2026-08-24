package splitwise.entity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Group {
    private final String groupId;
    private final String name;
    private final String createdBy;
    private final Set<String> membersIds;
    private final LocalDateTime createdAt;

    public Group(String name,
                 String createdBy,
                 LocalDateTime createdAt) {
        this.groupId = UUID.randomUUID().toString();
        this.name = name;
        this.createdBy = createdBy;
        this.membersIds = new HashSet<>(Collections.singleton(createdBy));
        this.createdAt = createdAt;
    }

    public String addMember(String memberId) {
        if (membersIds.add(memberId)) {
            return "Member added successfully";
        }
        return "Member already exists";
    }

    public String getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Set<String> getMembersIds() {
        return Set.copyOf(membersIds);
    }
}
