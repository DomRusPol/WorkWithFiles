package com.mainacad;

import com.mainacad.helper.ConnectionInfoHelper;
import com.mainacad.model.ConnectionInfo;
import com.mainacad.service.FileService;

import java.util.Date;
import java.util.logging.Logger;

public class ApplicationRunner {

	private static final Logger LOGGER = Logger.getLogger(ApplicationRunner.class.getName());

	public static void main(String[] args) {
//		LOGGER.info("" + new Date(0L));

		for (int i = 0; i < 10; i++) {
			ConnectionInfo connectionInfo = ConnectionInfoHelper.getRandomConnectionInfo();
			FileService.writeTextToFile(connectionInfo.toString(), "connections.txt", true);
			
//			FileService.readTextFromFile("sometext.txt");
		}

	}

}

// 1. ���, ������� ��������� ������? 2. ���, ������� �� ���� ���������� ��������� ������? 3. ������� �8 - 2 �������. (������� ����� ������� � ...) 4. ����� ������(��������? ��� ��������� ������
// ����� � �����������?)