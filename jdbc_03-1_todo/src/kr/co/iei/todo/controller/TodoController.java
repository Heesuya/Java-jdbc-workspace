package kr.co.iei.todo.controller;

import java.util.ArrayList;
import java.util.Scanner;

import kr.co.iei.todo.dao.TodoDao;
import kr.co.iei.todo.vo.Todo;

public class TodoController {
	Scanner sc = null;
	TodoDao todoDao = null;
	public TodoController() {
		super();
		sc = new Scanner(System.in);
		todoDao = new TodoDao();
	}
	
	public void main() {
		while(true) {
			System.out.println("1. 전체 할일 목록 보기");
			System.out.println("2. 완료 목록 보기");
			System.out.println("3. 미완료 목록 보기");
			System.out.println("4. 할일 등록 하기");
			System.out.println("5. 할일 완료 처리 하기");
			System.out.println("6. 할일 삭제 하기");
			System.out.println("0. 프로그램 종료");
			System.out.print("선택 >> ");
			int select = sc.nextInt();
			
			switch(select) {
			case 1:
				selectTodoAll();
				break;
			case 2:
				selectTodoY();
				//selectTodoCom("Y");
				break;
			case 3:
				selectTodoN();
				//selectTodoCom("N");
				break;
			case 4:
				insertTodo();
				break;
			case 5:
				updateTodo();
				break;
			case 6:
				deleteTodo();
				break;
			case 0:
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		}
	}//main();
	
	private void selectTodoCom(String string) {
		System.out.println("\n---------- 완료 목록 보기 ----------\n");
		ArrayList<Todo> list = todoDao.selectTodoCom(string);
		System.out.println("번호\t상태\t날짜\t\t작성자\t내용");
		System.out.println("--------------------------------------------");
		for(Todo t : list) {
			System.out.println(t.getTodoNo()+"\t"+t.getTodoDone()+"\t"+t.getTodoDate()+
					"\t"+t.getTodoWriter()+"\t"+t.getTodoContent());
		}
	}

	private void deleteTodo() {
		System.out.println("\n---------- 할일 삭제 하기 ----------\n");
		System.out.print("삭제 할 할일 번호를 입력하세요 : ");
		int todoNo = sc.nextInt();
		int result = todoDao.deleteDoto(todoNo);
	}

	public void selectTodoAll() {
		System.out.println("\n---------- 전체 할일 보기 ----------\n");
		ArrayList<Todo> list = todoDao.selectTodoAll();
		if(list.isEmpty()){
			System.out.println("등록된 정보가 없습니다.");
		}else {
			System.out.println("번호\t상태\t날짜\t\t작성자\t내용");
			for(Todo t : list) {
				System.out.println(t.getTodoNo()+"\t"+t.getTodoDone()+"\t"+t.getTodoDate()+
						"\t"+t.getTodoWriter()+"\t"+t.getTodoContent());
			}
		}
	}//selectTodoAll();
	
	public void insertTodo() {
		System.out.println("\n---------- 할일 등록 하기 ----------\n");
		System.out.print("할일 내용을 입력하세요 : ");
		sc.nextLine();
		String todoContent = sc.nextLine();
		System.out.print("작성자를 입력하세요 : ");
		String todoWriter = sc.next();
		
		Todo t = new Todo();
		t.setTodoContent(todoContent);
		t.setTodoWriter(todoWriter);
		
		int result = TodoDao.insertTodo(t);
		if(result > 0) {
			System.out.println("할일 등록되었습니다.");
		}else {
			System.out.println("할일 등록 실패");
		}
	}//insertTodo();
	
	
	public void selectTodoY() {
		System.out.println("\n---------- 완료 목록 보기 ----------\n");
		ArrayList<Todo> list = todoDao.selectTodoCom("Y");
		System.out.println("번호\t상태\t날짜\t\t작성자\t내용");
		System.out.println("--------------------------------------------");
		for(Todo t : list) {
			System.out.println(t.getTodoNo()+"\t"+t.getTodoDone()+"\t"+t.getTodoDate()+
					"\t"+t.getTodoWriter()+"\t"+t.getTodoContent());
		}
	}
	
	public void selectTodoN() {
		System.out.println("\n---------- 미완료 목록 보기 ----------\n");
		ArrayList<Todo> list = todoDao.selectTodoCom("N");
		System.out.println("번호\t상태\t날짜\t\t작성자\t내용");
		System.out.println("--------------------------------------------");
		for(Todo t : list) {
			System.out.println(t.getTodoNo()+"\t"+t.getTodoDone()+"\t"+t.getTodoDate()+
					"\t"+t.getTodoWriter()+"\t"+t.getTodoContent());
		}
	}
	
	public void updateTodo() {
		System.out.println("\n---------- 할일 완료 하기 ----------\n");
		System.out.println("완료 처리 할 할일 번호를 입력하세요 : ");
		int todoNo = sc.nextInt();
		int result = todoDao.updateTodo(todoNo);
		if(result > 0) {
			System.out.println("변경 완료");
		}else {
			System.out.println("번호를 확인하세요.");
		}
	}//updateTodo();
	
}
