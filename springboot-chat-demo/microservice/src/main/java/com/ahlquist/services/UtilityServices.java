package com.ahlquist.services;

import org.springframework.stereotype.Service;

//import com.ahlquist.models.UniqueId;
//import com.ahlquist.repositories.UniqueIdRepository;

@Service
public class UtilityServices {
//	@Autowired
//	private UniqueIdRepository uniqueIdRepository;
//
//	@Transactional
//	public String getUniqueIdentifier() {
//		String uniqueId = String.format("%s-%s", RandomStringUtils.randomAlphanumeric(4),
//				UUID.randomUUID().toString().replace("-", ""));
//		// you could left this check
//		while (uniqueIdRepository.existsById(uniqueId)) {
//			uniqueId = UUID.randomUUID().toString().replace("-", "");
//		}
//		uniqueIdRepository.save(new UniqueId(uniqueId));
//		return uniqueId;
//	}
}