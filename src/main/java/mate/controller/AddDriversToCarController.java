package mate.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.lib.Injector;
import mate.model.Car;
import mate.model.Driver;
import mate.service.CarService;
import mate.service.DriverService;

@WebServlet(urlPatterns = "/cars/drivers/add")
public class AddDriversToCarController extends HttpServlet {
    private static final String PATH_TO_JSP = "/WEB-INF/views/car/addDriverToCar.jsp";
    private static final Injector injector = Injector.getInstance("mate");
    private final CarService carService = (CarService) injector.getInstance(CarService.class);
    private final DriverService driverService
            = (DriverService) injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(PATH_TO_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Car car = carService.get(Long.valueOf(req.getParameter("car_id")));
        Driver driver = driverService.get(Long.valueOf(req.getParameter("driver_id")));
        car.getDrivers().add(driver);
        carService.update(car);
        resp.sendRedirect(req.getContextPath() + "/cars");
    }
}
