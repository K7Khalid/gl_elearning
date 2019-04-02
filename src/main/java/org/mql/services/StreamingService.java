package org.mql.services;

import java.util.List;

import org.mql.models.Member;
import org.mql.models.Streaming;

public interface StreamingService {
	Streaming findById(int id);
	boolean isAllowed(Streaming streaming,Member member);
	Streaming save(Streaming s);
	List<Streaming> findByAssistances(Member member);
	
}
