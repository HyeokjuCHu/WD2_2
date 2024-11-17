package org.example.hisnet;

import org.example.hisnet.JDBCUtil;
import org.example.hisnet.SubjectVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {
    private Connection conn;

    public SubjectDAO() {
        try {
            conn = JDBCUtil.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void add(SubjectVO subject) {
        String sql = "INSERT INTO subjects (category, code, name, englishRatio, credits, classNum, professor, classTime, classRoom, grade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, subject.getCategory());
            pstmt.setString(2, subject.getCode());
            pstmt.setString(3, subject.getName());
            pstmt.setInt(4, subject.getEnglishRatio());
            pstmt.setInt(5, subject.getCredits());
            pstmt.setString(6, subject.getClassNum());
            pstmt.setString(7, subject.getProfessor());
            pstmt.setString(8, subject.getClassTime());
            pstmt.setString(9, subject.getClassRoom());
            pstmt.setString(10, subject.getGrade());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // 모든 과목 조회
    public List<SubjectVO> list() {
        List<SubjectVO> subjects = new ArrayList<>();
        String sql = "SELECT * FROM subjects";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                SubjectVO subject = new SubjectVO();
                subject.setId(rs.getInt("id"));
                subject.setCategory(rs.getString("category"));
                subject.setCode(rs.getString("code"));
                subject.setName(rs.getString("name"));
                subject.setEnglishRatio(rs.getInt("englishRatio"));
                subject.setCredits(rs.getInt("credits"));
                subject.setClassNum(rs.getString("classNum"));
                subject.setProfessor(rs.getString("professor"));
                subject.setClassTime(rs.getString("classTime"));
                subject.setClassRoom(rs.getString("classRoom"));
                subject.setGrade(rs.getString("grade"));
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    // 특정 과목 조회
    public SubjectVO get(int id) {
        SubjectVO subject = null;
        String sql = "SELECT * FROM subjects WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    subject = new SubjectVO();
                    subject.setId(rs.getInt("id"));
                    subject.setCategory(rs.getString("category"));
                    subject.setCode(rs.getString("code"));
                    subject.setName(rs.getString("name"));
                    subject.setEnglishRatio(rs.getInt("englishRatio"));
                    subject.setCredits(rs.getInt("credits"));
                    subject.setClassNum(rs.getString("classNum"));
                    subject.setProfessor(rs.getString("professor"));
                    subject.setClassTime(rs.getString("classTime"));
                    subject.setClassRoom(rs.getString("classRoom"));
                    subject.setGrade(rs.getString("grade"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }

    // 과목 수정
// 과목 수정
    public int update(SubjectVO subject) {
        String sql = "UPDATE subjects SET category = ?, code = ?, name = ?, englishRatio = ?, credits = ?, classNum = ?, professor = ?, classTime = ?, classRoom = ?, grade = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, subject.getCategory());
            pstmt.setString(2, subject.getCode());
            pstmt.setString(3, subject.getName());
            pstmt.setInt(4, subject.getEnglishRatio());
            pstmt.setInt(5, subject.getCredits());
            pstmt.setString(6, subject.getClassNum());
            pstmt.setString(7, subject.getProfessor());
            pstmt.setString(8, subject.getClassTime());
            pstmt.setString(9, subject.getClassRoom());
            pstmt.setString(10, subject.getGrade());
            pstmt.setInt(11, subject.getId());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    // 과목 삭제
    public void delete(int id) {
        String sql = "DELETE FROM subjects WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 데이터베이스 연결 종료
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
