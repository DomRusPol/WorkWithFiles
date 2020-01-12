package com.mainacad.service;

import com.mainacad.helper.ConnectionInfoHelper;
import com.mainacad.model.ConnectionInfo;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {

	private static final String TEXT_FILE_NAME = "test_text_file.txt";
	private static final String BYTES_FILE_NAME = "test_bytes_file.obj";

	@BeforeAll
	static void setUpBeforeAll() {
		byte[] testBytes = FileService.getBytesFromFile("cat.jpg");
		FileService.writeBytesToFile(testBytes, BYTES_FILE_NAME);
	}

	@BeforeEach
	void setUp() {
		FileService.writeTextToFile("", TEXT_FILE_NAME, false);

		ConnectionInfo connectionInfo = ConnectionInfoHelper.getRandomConnectionInfo();
		FileService.writeTextToFile(connectionInfo.toString(), TEXT_FILE_NAME, false);
	}

	@AfterEach
	void tearDown() {
		File testTextFile = new File(FileService.FILES_DIR + FileService.SEP + TEXT_FILE_NAME);
		testTextFile.delete();
	}

	@AfterAll
	static void tearDownAfterAll() {
		File testBytesFile = new File(FileService.FILES_DIR + FileService.SEP + BYTES_FILE_NAME);
		testBytesFile.delete();
	}

	@Test
	void readTextFromFile() {
		String testText = FileService.readTextFromFile(TEXT_FILE_NAME);

		assertNotNull(testText);
		assertTrue(testText.contains(" "));
		assertTrue(testText.length() > 22);
	}

	@Test
	void readConectionsFromFile() {
		List<ConnectionInfo> testObj = FileService.readConectionsFromFile(TEXT_FILE_NAME);
		assertNotNull(testObj);
		assertTrue(testObj.size() > 0);
		assertNotNull(testObj.get(0).getConnectionTime());
		assertNotNull(testObj.get(0).getIp());
		assertNotNull(testObj.get(0).getSessionId());
		assertTrue(testObj.get(0).getSessionId() > 10000 && testObj.get(0).getSessionId() < 99999);
	}

	@Test
	void getBytesFromFile() {
		byte[] testBytes = FileService.getBytesFromFile(BYTES_FILE_NAME);

		assertNotNull(testBytes);
		assertTrue(testBytes.length > 0);
	}

	@Test
	void deleteExpiredData() {

		ConnectionInfo connectionInfo = new ConnectionInfo();
		connectionInfo.setConnectionTime(new Date().getTime());

		ConnectionInfo connectionInfo2 = new ConnectionInfo();
		connectionInfo2.setConnectionTime(new Date().getTime() - 1000 * 60 * 60 * 24 * 4);

		FileService.writeTextToFile(connectionInfo.toString(), connectionInfo2.toString(), true);

		FileService.deleteExpiredData(new Date().getTime() - 1000 * 60 * 60 * 24 * 3);
		List<ConnectionInfo> connections = FileService.readConectionsFromFile("test_text_file.txt");
		assertTrue(connections.size() == 1);
		assertEquals(connections.get(0).getConnectionTime() == connectionInfo.getConnectionTime());

	}

}
