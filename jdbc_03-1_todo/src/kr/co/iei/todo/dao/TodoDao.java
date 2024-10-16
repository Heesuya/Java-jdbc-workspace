package kr.co.iei.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.iei.todo.vo.Todo;

public class TodoDao {
	
	//할일 넣기 
	public static int insertTodo(Todo t) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into todo_tbl values(todo_seq.nextval,?,?,'N',sysdate)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc_ex1","1111");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, t.getTodoContent());
			pstmt.setString(2, t.getTodoWriter());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		return result;
	}//insertTodo(); 할일 등록 

	public ArrayList<Todo> selectTodoAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from todo_tbl order by todo_no";
		ArrayList<Todo> list = new ArrayList<Todo>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc_ex1","1111");
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Todo t = new Todo();
				t.setTodoNo(rset.getInt("todo_no"));
				t.setTodoContent(rset.getString("todo_content"));
				t.setTodoWriter(rset.getString("todo_writer"));
				t.setTodoDone(rset.getString("todo_done"));
				t.setTodoDate(rset.getDate("todo_date"));
				list.add(t);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return list;
	}//selectTodoAll();

	public ArrayList<Todo> selectTodoY() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		
		String query = "select * from todo_tbl where todo_done = 'Y'";
		
		ArrayList<Todo> list = new ArrayList<Todo>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc_ex1","1111");
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Todo t = new Todo();
				t.setTodoContent(rset.getString("todo_content"));
				t.setTodoDate(rset.getDate("todo_date"));
				t.setTodoDone(rset.getString("todo_done"));
				t.setTodoNo(rset.getInt("todo_no"));
				t.setTodoWriter(rset.getString("todo_writer"));
				list.add(t);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}//selectTodoY();

	public ArrayList<Todo> selectTodoN() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		
		String query = "select * from todo_tbl where todo_done = 'N'";
		
		ArrayList<Todo> list = new ArrayList<Todo>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc_ex1","1111");
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Todo t = new Todo();
				t.setTodoContent(rset.getString("todo_content"));
				t.setTodoDate(rset.getDate("todo_date"));
				t.setTodoDone(rset.getString("todo_done"));
				t.setTodoNo(rset.getInt("todo_no"));
				t.setTodoWriter(rset.getString("todo_writer"));
				list.add(t);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}//selectTodoN();

	public int updateTodo(int todoNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update todo_tbl set todo_done = 'Y' where todo_no = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc_ex1","1111");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, todoNo);
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteDoto(int todoNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String qurey = "delete from todo_tbl where todo_no = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc_ex1","1111");
			pstmt = conn.prepareStatement(qurey);
			pstmt.setInt(1, todoNo);
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<Todo> selectTodoCom(String string) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		
		String query = "select * from todo_tbl where todo_done = ?";
		
		ArrayList<Todo> list = new ArrayList<Todo>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc_ex1","1111");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, string);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Todo t = new Todo();
				t.setTodoContent(rset.getString("todo_content"));
				t.setTodoDate(rset.getDate("todo_date"));
				t.setTodoDone(rset.getString("todo_done"));
				t.setTodoNo(rset.getInt("todo_no"));
				t.setTodoWriter(rset.getString("todo_writer"));
				list.add(t);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
