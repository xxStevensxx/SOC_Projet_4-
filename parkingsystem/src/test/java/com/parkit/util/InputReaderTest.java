package com.parkit.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.parkit.parkingsystem.util.InputReaderUtil;

public class InputReaderTest {
	

	private static InputReaderUtil inputReaderUtil;	

	@BeforeEach
	private  void setUpBefTest() {
		 inputReaderUtil = new InputReaderUtil();
	}
	
	@Test
	public void readSelectionChoiceOK() {
		//GIVEN
		ByteArrayInputStream input = new ByteArrayInputStream("1\n".getBytes(Charset.forName("UTF-8")));
		Scanner sc = new Scanner(input);
		inputReaderUtil.setScan(sc);
		
		//WHEN
//		le 1 correspond au retour de la methode ByteArrayInputStream si  String return-1 sinon 1
		int result = 1;
		
		//THEN
		assertThat(inputReaderUtil.readSelection()).isEqualTo(result);
		
	}
	
	@Test
	public void readSelectionChoiceKO() {
		//GIVEN
		try {
			ByteArrayInputStream input = new ByteArrayInputStream("badValue\n".getBytes(Charset.forName("UTF-8")));
			Scanner sc = new Scanner(input);
			inputReaderUtil.setScan(sc);
		} catch (Exception e) {
			// TODO: handle exception
			
		}
	
		
		//WHEN
//		le 1 correspond au retour de la methode ByteArrayInputStream si  String return-1 sinon 1
		int result = -1;
		
		//THEN
		assertThat(inputReaderUtil.readSelection()).isEqualTo(result);
		
	}

	
	@Test
	public void readSelectionVehiculeREGTestOK() throws Exception {
		//GIVEN
		ByteArrayInputStream input = new ByteArrayInputStream("RX-7\n".getBytes(Charset.forName("UTF-8")));
		Scanner sc = new Scanner(input);
		inputReaderUtil.setScan(sc);
		
		//WHEN
//		le 1 correspond au retour de la methode ByteArrayInputStream si  String return-1 sinon 1
		String result = "RX-7";
		
		//THEN
		assertThat(inputReaderUtil.readVehicleRegistrationNumber()).isEqualTo(result);
		
	}
	
	@Test
	public void readSelectionVehiculeREGTestKO() throws Exception {
		//GIVEN
		ByteArrayInputStream input = new ByteArrayInputStream("\n".getBytes(Charset.forName("UTF-8")));
		Scanner sc = new Scanner(input);
		inputReaderUtil.setScan(sc);
		
		//WHEN
//		le 1 correspond au retour de la methode ByteArrayInputStream si  String return-1 sinon 1
//		int result = -1;
		
		//THEN
		assertThrows(IllegalArgumentException.class , () -> inputReaderUtil.readVehicleRegistrationNumber(),"Invalid input provided");
		
	}
}
