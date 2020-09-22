package com.parkit.parkingsystem.shell.integration;

import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.service.InteractiveShell;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class InteractiveShellIT {
	

//   private static ParkingService parkingService;

	@Mock
	private static InputReaderUtil inputReaderUtil;
    @Mock
    private static TicketDAO ticketDAO;
    @Mock
    private static ParkingSpotDAO parkingSpotDAO;
    @Mock
    private static ParkingService parkingService;

	
	
	@BeforeEach
	public void setUpInputAppInterface() throws Exception {
		when(inputReaderUtil.readSelection()).thenReturn(1);

	}
	
	@Disabled
	@Test
	public void loadInterfaceTestIT(){
//		ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
		
		InteractiveShell interactiveShell = mock(InteractiveShell.class);
		
		InteractiveShell.loadInterface();
		
//		 doAnswer().when(interactiveShell);
//		InteractiveShell.loadInterface();
//		    InteractiveShell.loadInterface();
		 
		    verify(interactiveShell, times(1));
//			InteractiveShell.loadInterface();
		//Assert
//        verify(parkingService.processIncomingVehicle(), Mockito.times(1));
//        Mockito.verify(parkingService.processIncomingVehicle(), Mockito.never()).parkingService.processIncomingVehicle(Mockito.anyString());
        
	}
}
