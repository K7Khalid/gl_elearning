package org.mql.controllers.rest;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.mql.models.Comment;
import org.mql.models.Streaming;
import org.mql.services.MemberService;
import org.mql.services.StreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StreamingRESTController {
	
	@Autowired MemberService memberService;
	@Autowired StreamingService streamingService;
	
	@PostMapping("/comment")
	public ResponseEntity<?> addComment(@RequestBody Map<String,String> body,Principal principal) {
		Comment comment = new Comment();
		System.out.println(body.values());
		int id = Integer.parseInt(body.get("stream_id"));
		Streaming streaming = streamingService.findById(id);
		comment.setMember(memberService.findByEmail(principal.getName()));
		comment.setDateText();
		comment.setContent(body.get("content"));
		streaming.addComment(comment);
		streamingService.save(streaming);

		return ResponseEntity.ok(comment);
	}
}
