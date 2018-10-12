package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;

import rental.ICarRentalCompany;
import rental.Quote;
import rental.Reservation;

public class Client extends AbstractTestBooking {
	
	/********
	 * MAIN *
	 ********/

	public static void main(String[] args) throws Exception {


		String carRentalCompanyName = "Hertz";

		// An example reservation scenario on car rental company 'Hertz' would be...
		Client client = new Client("simpleTrips", carRentalCompanyName);
		client.run();
	}
	
	/***************
	 * CONSTRUCTOR *
	 ***************/
	ICarRentalCompany crc;
	public Client(String scriptFile, String carRentalCompanyName) throws RemoteException, NotBoundException {
		super(scriptFile);
		// TODO Auto-generated method stub;
		System.setSecurityManager(null);
		Registry registry = LocateRegistry.getRegistry("localhost", 0);
		crc = (ICarRentalCompany) registry.lookup("ICarRentalCompany");

	}
	
	/**
	 * Check which car types are available in the given period
	 * and print this list of car types.
	 *
	 * @param 	start
	 * 			start time of the period
	 * @param 	end
	 * 			end time of the period
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
	@Override
	protected void checkForAvailableCarTypes(Date start, Date end) throws Exception {
		System.out.println(crc.getAvailableCarTypes(start,end));
	}

	/**
	 * Retrieve a quote for a given car type (tentative reservation).
	 * 
	 * @param	clientName 
	 * 			name of the client 
	 * @param 	start 
	 * 			start time for the quote
	 * @param 	end 
	 * 			end time for the quote
	 * @param 	carType 
	 * 			type of car to be reserved
	 * @param 	region
	 * 			region in which car must be available
	 * @return	the newly created quote
	 *  
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
	@Override
	protected Quote createQuote(String clientName, Date start, Date end,
			String carType, String region) throws Exception {
		crc.createQuote(crc.getReservationConstraints(start,end,carType, region),clientName);
		System.out.println("client name:" + clientName);
		System.out.println("start:" + start);
		System.out.println("end" + end);
		System.out.println("car type:" + carType);
		System.out.println("region:" + region);
		return null;
	}

	/**
	 * Confirm the given quote to receive a final reservation of a car.
	 * 
	 * @param 	quote 
	 * 			the quote to be confirmed
	 * @return	the final reservation of a car
	 * 
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
	@Override
	protected Reservation confirmQuote(Quote quote) throws Exception {
		return crc.confirmQuote(quote);
	}
	
	/**
	 * Get all reservations made by the given client.
	 *
	 * @param 	clientName
	 * 			name of the client
	 * @return	the list of reservations of the given client
	 * 
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
	@Override
	protected List<Reservation> getReservationsByRenter(String clientName) throws Exception {
		System.out.println(crc.getReservationRenter(clientName));
		return null;
	}

	/**
	 * Get the number of reservations for a particular car type.
	 * 
	 * @param 	carType 
	 * 			name of the car type
	 * @return 	number of reservations for the given car type
	 * 
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
	@Override
	protected int getNumberOfReservationsForCarType(String carType) throws Exception {
		System.out.println( "Number of reservation for cartype" + carType + ":" + crc.getNumberOfReservationsCar(carType));
		return 0;
	}
}