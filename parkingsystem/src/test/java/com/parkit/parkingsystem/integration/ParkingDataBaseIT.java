package com.parkit.parkingsystem.integration;

import com.parkit.parkingsystem.cast.StuffCast;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class ParkingDataBaseIT {

	private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
    private static ParkingSpotDAO parkingSpotDAO;
    private static TicketDAO ticketDAO;
    private static DataBasePrepareService dataBasePrepareService;

    @Mock
    private static InputReaderUtil inputReaderUtil;

    @BeforeAll
    private static void setUp() throws Exception{
        parkingSpotDAO = new ParkingSpotDAO();
        parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
        ticketDAO = new TicketDAO();
        ticketDAO.dataBaseConfig = dataBaseTestConfig;
        dataBasePrepareService = new DataBasePrepareService();
    }

    @BeforeEach
    private void setUpPerTest() throws Exception {
        when(inputReaderUtil.readSelection()).thenReturn(1);
        when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("RX-8");
        dataBasePrepareService.clearDataBaseEntries();
    }

    @AfterAll
    private static void tearDown() throws Exception{
//        dataBasePrepareService.clearDataBaseEntries();
    }


    @Test
    public void testParkingACar() throws Exception{
        ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
        parkingService.processIncomingVehicle();
        //TODO: check that a ticket is actualy saved in DB and Parking table is updated with availability
        
        //GIVEN
       String vehicle = inputReaderUtil.readVehicleRegistrationNumber();
//		ticketDAO.getTicket(vehicleRegNumber).toString();
       
       //ASSERT
       assertEquals(vehicle, ticketDAO.getTicket(vehicle).getVehicleRegNumber());
       assertEquals(ticketDAO.getTicket(vehicle).getParkingSpot().isAvailable(),false);
    }
    

    @Test
    public void testParkingLotExit() throws Exception{
    	testParkingACar();
    	
    	ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    	
    	//Permet de mettre une petit temps dattente avant lexecution de la prochaine sequence
        Thread waiter = new Thread();
        waiter.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        parkingService.processExitingVehicle();
        
        //TODO: check that the fare generated and out time are populated correctly in the database
        
        //GIVEN
        String vehicle = inputReaderUtil.readVehicleRegistrationNumber();
        
        
        //ASSERT
        assertEquals(0.0, ticketDAO.getTicket(vehicle).getPrice());
        //On verifie que la date de sortie du vehicule n'est pas NULL
        assertNotEquals(ticketDAO.getTicket(vehicle).getOutTime(), StuffCast.getDateCast());
    }

}
