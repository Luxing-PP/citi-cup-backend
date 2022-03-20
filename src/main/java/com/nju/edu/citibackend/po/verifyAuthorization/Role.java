package com.nju.edu.citibackend.po.verifyAuthorization;


import lombok.*;

/**
 * @author kevin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Role {
	private Integer id;

	private String name;

	private String comment;

}
