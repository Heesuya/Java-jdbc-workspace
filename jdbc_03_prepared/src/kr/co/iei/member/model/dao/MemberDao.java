package kr.co.iei.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.iei.member.model.vo.Member;

public class MemberDao {

	//아이디로 멤버 찾기 메소드 
	public Member selectMemberId(String searchId) {
		//0-1. 반환 할 객체 선언
		Connection conn = null;
		//Statement stmt = null;
		//PreparedStatement 는 Statement를 상속해서 만들어진 객체임
		PreparedStatement pstmt = null;//쿼리문을 수행하고 결과를 받아오는 객체(Statement -> PreparedStatement)
		ResultSet rset = null;
		//0-2. 리턴할 객체 선언 
		Member m = null;
		//0-3. 쿼리문 작성
		//select * from member_tbl where member_id='입력한아이디';
		//String query = "select * from member_tbl where member_id='"+searchId+"'";
		//PreparedStatement에서는 쿼리문에서 변수로 값을 넣어줘야하는 부분을 ?로 표시 
		String query = "select * from member_tbl where member_id = ?";
		// ? : 위치홀더 
		// 위치홀더는 리터럴을 대체 
		// -> 값에 대한 부분만 대체가 가능(테이블이름, 컬럼이름, 조건절에서 사용하는 컬럼이름, 쿼리문 구성 X)
		// -> 위치홀더에 어떤값이 들어가더라도 쿼리문 문법에 변화가 없어야 함 
		// 위치홀더(?) : sql구문에 나타내는 토큰, 실제 sql 구문이 실행되기 전에 값으로 전부 대체되어야 함 
		// -> 값으로 대체하지않고 쿼리를 실행하면 에러가 발생 
		
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. Connection 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc_test","1234");
			//3. Statement 생성 -> PreparedStatement 생성
			//stmt = conn.createStatement();
			//3. PreparedStatement 생성
			pstmt = conn.prepareStatement(query); //PreparedStatement객체를 생성할때 쿼리를 전달하면서 생성
			//3-1. PreparedStatement객체가 받은 쿼리는 위치홀더가 들어간쿼리 
			// -> 실행전에 위치홀더에 값을 대입
			pstmt.setString(1,searchId);//1번 위치홀더에 문자열데이터 searchId값을 대입 
			//모든 위치홀더에 값을 대입하고나면 실행
			//4,5. 쿼리문 실행하고 결과 저장 
			//rset = stmt.executeQuery(query);
			// 상속을 하기때문에 자동완성에 있어서 쓰면 안됨 . pstmt.executeQuery(query);
			rset = pstmt.executeQuery();//쿼리 실행할때 매개변수로 쿼리를 전달하지 않음 
			if(rset.next()) {
				m = new Member();
				m.setEnrollDate(rset.getDate("enroll_Date"));
				m.setMemberAge(rset.getInt("member_age"));
				m.setMemberGender(rset.getString("member_gender"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setMemberPw(rset.getNString("member_pw"));
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
				//stmt.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return m;
	}//Member selectMemberId(String searchId) ;

	public int insertMember(Member m) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = "insert into member_tbl values(?,?,?,?,?,?,sysdate)";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc_test","1234");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getMemberPhone());
			pstmt.setInt(5, m.getMemberAge());
			pstmt.setString(6, m.getMemberGender());	
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

	//전체 회원 조회
	public ArrayList<Member> selcetAllMember() {
		Connection conn = null;
		//Statement stmt = null;   이제 사용 안함. 
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from member_tbl";
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc_test","1234");
			pstmt = conn.prepareStatement(query);
			
			while(rset.next()) {
				Member m = new Member();
				m.setEnrollDate(rset.getDate("enroll_date"));
				m.setMemberAge(rset.getInt("member_age"));
				m.setMemberGender(rset.getString("member_gender"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setMemberPw(rset.getString("member_pw"));
				list.add(m);
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
	}//ArrayList<Member> selcetAllMember();

	public ArrayList<Member> selectMemberName(String searchName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Member> list = new ArrayList<Member>();
		//2. DB에서 문자열 만드는 방법 
		String query = "select * from member_tbl where member_name like '%'||?||'%'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc_test","1234");
			pstmt = conn.prepareStatement(query);
			//1. 자바에서 문자열 만드는 방법 
			//pstmt.setString(1, "%"+searchName+"%");
			pstmt.setString(1, searchName);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setEnrollDate(rset.getDate("enroll_date"));
				m.setMemberAge(rset.getInt("member_age"));
				m.setMemberGender(rset.getString("member_gender"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setMemberPw(rset.getString("member_pw"));
				list.add(m);	
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
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}//selectMemberName();

	public int updateMember(Member m) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update member_tbl set member_pw = ? , member_name = ? , member_phone = ? where member_id = ?";
				
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc_test","1234");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberPw());
			pstmt.setString(2, m.getMemberName());
			pstmt.setString(3, m.getMemberPhone());
			pstmt.setString(4, m.getMemberId());
			
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
	}//updateMember();

	public int deleteMember(String deleteId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete from member_tbl where member_name = ?";
					
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jdbc_test","1234");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, deleteId);
			
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

}
