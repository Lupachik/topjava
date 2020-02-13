package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImplMemory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/edit.jsp";
    private static String LIST_MEAL = "/meals.jsp";
    private MealDao mealDao;

    // initialisation mealDao
    @Override
    public void init() throws ServletException {
        super.init();
        mealDao = new MealDaoImplMemory();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action == null) {
            log.debug("forward to meals");
            forward = LIST_MEAL;
            List<MealTo> mealToList = MealsUtil.filteredByStreams(mealDao.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
            request.setAttribute("mealList", mealToList);
        } else if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            mealDao.delete(mealId);
            log.debug("delete meal");
            response.sendRedirect("meals");
            return;
        } else if (action.equalsIgnoreCase("edit")) {
            log.debug("edit");
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = mealDao.getById(mealId);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("create")) {
            log.debug("create");
            forward = INSERT_OR_EDIT;
            request.setAttribute("meals", mealDao.getAll());
        } else {
            log.debug("bad command");
            response.sendRedirect("meals");
            return;
        }

        request.getRequestDispatcher(forward).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String mealId = request.getParameter("mealId");
        if (mealId == null || mealId.isEmpty()) {
            log.debug("add meal");
            Meal meal = new Meal(LocalDateTime.parse(request.getParameter("date")), request.getParameter("description"),
                    Integer.parseInt(request.getParameter("calories")));
            mealDao.add(meal);
        } else {
            log.debug("edit meal");
//            mealDao.delete(Integer.parseInt(mealId));
            Meal meal = new Meal(Integer.parseInt(mealId), LocalDateTime.parse(request.getParameter("date")),
                    request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
            mealDao.update(meal);
        }

        response.sendRedirect("meals");
    }
}
