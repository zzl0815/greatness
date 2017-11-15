package com.zzl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzl.bean.Reward;
import com.zzl.repository.RewardRepository;
import com.zzl.service.RewardService;
@Service
public class RewardServiceImpl implements RewardService{
	
	@Autowired
	RewardRepository rewardRepository;
	@Override
	public Reward saveReward(Reward reward) {
		reward =rewardRepository.save(reward);
		return reward;
	}

}
