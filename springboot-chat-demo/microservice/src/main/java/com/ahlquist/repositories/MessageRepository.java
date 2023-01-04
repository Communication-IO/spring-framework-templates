package com.ahlquist.repositories;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ahlquist.models.Message;

@Repository(value = "MessageRepository")
public interface MessageRepository extends CrudRepository<Message, Integer> {

	/**
	 * Fetch all existing messages to a given recipient, within a range of message
	 * IDs.
	 * 
	 * @param recipient - UUID User ID of recipient.
	 * @param start     - Integer, Starting message ID. Messages will be returned in
	 *                  increasing order of message ID, starting from this value (or
	 *                  the next lowest value stored in the database).
	 * @param limit     - Integer Default: 100 Limit the response to this many
	 *                  messages.
	 * @return - List of Message values
	 */
	@Query(value = "SELECT * FROM message m WHERE m.recipient = :recipient AND m.id >= :start ORDER BY m.timestamp ASC LIMIT :limit", nativeQuery = true)
	public List<Message> findRecipientMesssageList(
			@NonNull @Param("recipient") UUID recipient,
			@NonNull @Param("start") Integer start, 
			@NonNull @Param("limit") Integer limit
			);
	
	@Query(value = "SELECT * FROM message m WHERE m.recipient = :uuid OR m.sender = :uuid AND id >= :start ORDER BY id ASC LIMIT :limit", nativeQuery = true)
	public List<Message> findSendersOrRecipientMesssageList(
			@NonNull @Param("uuid") UUID uuid,
			@NonNull @Param("start") Integer start, //the message id
			@NonNull @Param("limit") Integer limit
			);
	
	static String RECIPIENT_MESSAGE_LIST_BY_TOKEN = 
			"SELECT m.id, " + 
				"m.timestamp, " + 
				"m.sender,  " +
				"m.recipient, " +
				"m.contenttype, " + 
				"m.contenttext " +
			"FROM message m, user u " +
			"WHERE u.token = :token AND m.id >= :start " +
			"ORDER BY m.id " + 
			"ASC LIMIT :limit";
	
	@Query(value = RECIPIENT_MESSAGE_LIST_BY_TOKEN, nativeQuery = true)
	public List<Message> findRecipientMesssageListByUserToken(
			@NonNull @Param("token") String token,
			@NonNull @Param("start") Integer start, //the message id
			@NonNull @Param("limit") Integer limit
			);

	
	@Query(value = "SELECT * FROM message m WHERE m.id = :id", nativeQuery = true)
	public Optional<Message> findById(@NonNull @Param("id") Integer id);
		
    //query for all message between two timestamps for given sender and token
	@Query(value = "SELECT * FROM message m WHERE m.sender = :sender AND m.recipient = :recipient AND timestamp >= :timestampLower AND timestamp <= :timestampUpper ORDER BY timestamp ASC", nativeQuery = true)
	public List<Message> findByUuidsAndBetweenTimestamps(
			final @NonNull @Param("sender") UUID sender,
			final @NonNull @Param("recipient") UUID recipient,
			final @NonNull @Param("timestampLower") Timestamp timestampLower, 
			final @NonNull @Param("timestampUpper") Timestamp timestampUpper);
	
	//query for all message between two timestamps for given recipient and token
	//@Query(value = "SELECT * FROM message m WHERE m.recipient = :uuid OR m.sender = :uuid AND start >= :start ASC LIMIT :limit", nativeQuery = true)
	
}
