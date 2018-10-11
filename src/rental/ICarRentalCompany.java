package rental;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ICarRentalCompany extends Remote {
      public CarType getCarType(String carTypeName) throws RemoteException;

      public boolean isAvailable(String carTypeName, Date start, Date end) throws RemoteException;

      public Set<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException;

      public Quote createQuote(ReservationConstraints constraints, String client) throws RemoteException, ReservationException;

      public ReservationConstraints getReservationConstraints(Date start, Date end, String carType, String region) throws RemoteException;
      public Reservation confirmQuote(Quote quote) throws RemoteException, ReservationException;

      public void cancelReservation(Reservation res) throws RemoteException;

      public List<String> getReservationRenter(java.lang.String carRenter) throws RemoteException;

      public int getNumberOfReservationsCar(String carType) throws RemoteException;
}