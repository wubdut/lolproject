package com.service;

import java.util.ArrayList;
import java.util.List;

public class GroupNumSevice {
	
	public int getGroupId(List<Integer> sameDepaTeamNum) {
		
		if (sameDepaTeamNum == null || sameDepaTeamNum.size() == 0) return 0;
		
		List<Integer> randList = new ArrayList<Integer>();
		int minNum = Integer.MIN_VALUE;
		
		for (Integer num : sameDepaTeamNum) {
			minNum = num < minNum ? num : minNum;
		}
		
		for (int groupId = 0; groupId < sameDepaTeamNum.size(); groupId++) {
			if (sameDepaTeamNum.get(groupId) == minNum)
				randList.add(groupId);
		}
		
		return randList.get((int)(Math.random()*randList.size()));   // 部门平均，最终平均
	}

}
