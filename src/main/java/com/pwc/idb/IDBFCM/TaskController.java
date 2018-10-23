package com.pwc.idb.IDBFCM;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/tasks")
public class TaskController {
	Map<String, List<Task>> returnMap = new HashMap<>();
	 @RequestMapping("/add")
	public Map<String, List<Task>> addTask(@RequestBody Task task) {
		
		return null;

	}

}
