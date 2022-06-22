package com.qa.ims.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

/**
 * Takes in order details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;
	private CustomerController customerController;
	private ItemController itemController;

	public OrderController(OrderDAO orderDAO, Utils utils, CustomerController customerController, ItemController itemController) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	/**
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	/**
	 * Creates a order by taking in user input
	 */
	@Override
	public Order create() {
		Customer customer = this.customerController.create();
		ArrayList<Item> items = new ArrayList<Item>();
		while(true) {
			LOGGER.info("Do you want to create an item? (y)");
			String yes = utils.getString();
			
			if (yes == "y") {
				Item item = this.itemController.create();
				items.add(item);
			} else {
				break;
			}
		}
		
		Order order = orderDAO.create(new Order(customer, items));
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		ArrayList<Item> items = new ArrayList<Item>();
		Item item = this.itemController.create();
		items.add(item);
		Order order = orderDAO.update(new Order(orderName, orderValue));
		LOGGER.info("Order Updated");
		return order;
	}

	/**
	 * Deletes an existing order by the id of the order
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}

}

