package com.book.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@SuperBuilder
@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
@NoArgsConstructor
public class BaseCreateEntity {
	@Setter
	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@PrePersist
	public void prePersist(){
		LocalDateTime now = LocalDateTime.now();
		createdAt = now;
	}

}
