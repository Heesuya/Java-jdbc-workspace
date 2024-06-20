package kr.co.iei.todo.vo;

import java.sql.Date;

public class Todo {
	private int todoNo;
	private String todoContent;
	private String todoWriter;
	private String todoDone;
	private Date todoDate;
	public Todo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Todo(int todoNo, String todoContent, String todoWriter, String todoDone, Date todoDate) {
		super();
		this.todoNo = todoNo;
		this.todoContent = todoContent;
		this.todoWriter = todoWriter;
		this.todoDone = todoDone;
		this.todoDate = todoDate;
	}
	public int getTodoNo() {
		return todoNo;
	}
	public void setTodoNo(int todoNo) {
		this.todoNo = todoNo;
	}
	public String getTodoContent() {
		return todoContent;
	}
	public void setTodoContent(String todoContent) {
		this.todoContent = todoContent;
	}
	public String getTodoWriter() {
		return todoWriter;
	}
	public void setTodoWriter(String todoWriter) {
		this.todoWriter = todoWriter;
	}
	public String getTodoDone() {
		return todoDone;
	}
	public void setTodoDone(String todoDone) {
		this.todoDone = todoDone;
	}
	public Date getTodoDate() {
		return todoDate;
	}
	public void setTodoDate(Date todoDate) {
		this.todoDate = todoDate;
	}
	@Override
	public String toString() {
		return "Todo [todoNo=" + todoNo + ", todoContent=" + todoContent + ", todoWriter=" + todoWriter + ", todoDone="
				+ todoDone + ", todoDate=" + todoDate + "]";
	}
	
}
