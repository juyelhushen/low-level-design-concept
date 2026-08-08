package hotet_managment_system.design.template;

import hotet_managment_system.design.factory.PricingStrategyFactory;
import hotet_managment_system.design.strategy.pricing.PricingContext;
import hotet_managment_system.entity.Booking;
import hotet_managment_system.entity.DateRange;
import hotet_managment_system.entity.Payment;
import hotet_managment_system.entity.Room;
import hotet_managment_system.enums.PaymentMode;
import hotet_managment_system.repository.BookingRepository;
import hotet_managment_system.repository.PaymentRepository;
import hotet_managment_system.repository.RoomRepository;
import hotet_managment_system.repository.UserRepository;

public class InstantBookingProcessor extends BookingProcessor {

    private static final double INSTANT_SURCHARGE_PERCENT = 5.0;
    private final PricingContext pricingContext;

    public InstantBookingProcessor(UserRepository userRepo, RoomRepository roomRepo,
                                   BookingRepository bookingRepo, PaymentRepository paymentRepo,
                                   PricingContext pricingContext) {
        super(userRepo, roomRepo, bookingRepo, paymentRepo);
        this.pricingContext = pricingContext;
    }

    @Override
    protected void postProcess(Booking booking) {

    }

    @Override
    protected double calculateTotalPrice(Room room, DateRange dateRange) {
        pricingContext.setPricingStrategy(
                PricingStrategyFactory.getStrategy(dateRange.checkIn()));
        double basePrice = pricingContext.calculateTotal(room, dateRange);
        // surcharge for the instant confirmation guarantee
        return basePrice * (1 + INSTANT_SURCHARGE_PERCENT / 100);
    }

    @Override
    protected Payment processPayment(Booking booking, PaymentMode mode) {
        return null;
    }
}
