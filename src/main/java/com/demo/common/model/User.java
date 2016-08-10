package com.demo.common.model;

import com.demo.common.model.base.BaseUser;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class User extends BaseUser<User> {
	public static final User dao = new User();

	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	public String generateSalt(){
		return randomNumberGenerator.nextBytes().toHex();
	}

	public String entryptPassword(String password, String salt){
		return new Md5Hash(password, salt).toHex();
	}

	public void entryptPassword(User user){
		String salt = generateSalt();
		String hashPassword = entryptPassword(user.getPassword(), salt);
		user.setPassword(hashPassword);
		user.setSalt(salt);
	}
}