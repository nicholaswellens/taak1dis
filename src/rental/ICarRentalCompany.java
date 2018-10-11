package rental;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Set;

public interface ICarRentalCompany extends Remote {
       //public List<CarType> getFreeCarTypes(Date from, Date end) throws RemoteException;
      public CarType getCarType(String carTypeName) throws RemoteException;
      public boolean isAvailable(String carTypeName, Date start, Date end) throws RemoteException;
      public Set<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException;
      public Quote createQuote(ReservationConstraints constraints, String client) throws RemoteException;
      public Reservation confirmQuote(Quote quote) throws ReservationException, RemoteException {
}
