package org.seckill.daoconfigure;

import java.util.Properties;

import org.seckill.utils.AesUtil;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlaceHolderConfigure extends PropertyPlaceholderConfigurer {

	private String[] encryptProps = { "jdbc.username", "jdbc.password" };

	private boolean isEncryptProp(final String propertyName) {
		for (String prop : encryptProps) {
	
			if (prop.equals(propertyName)) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void convertProperties(Properties props) {
		// TODO Auto-generated method stub
		super.convertProperties(props);
	}

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		// TODO Auto-generated method stub
		if (isEncryptProp(propertyName)) {
			//String decryptValue = DesUtils.getDecryptString(propertyValue);
			//使用aes解密
			String decryptValue = AesUtil.aesDecrypt(propertyValue,"123456");
			return decryptValue;
		} else {
			return propertyValue;
		}
	}

}
