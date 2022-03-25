package com.tus.garbagesorting.garbagesorting.Mapper;

/**
 * This interface declares methods that are implemented in the InviteService class, to send out
 * invitation links.
 */

public interface InviteMapper {
    String GetInviteCode(int user_id);
}
