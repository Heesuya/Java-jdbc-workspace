package kr.co.iei.book.model.service;

import kr.co.iei.book.model.dao.RentalDao;

public class RentalService {
	private RentalDao rentalDao;

	public RentalService() {
		super();
		rentalDao = new RentalDao();
	}
	
}
