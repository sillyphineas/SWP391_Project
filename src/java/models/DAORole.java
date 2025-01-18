/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import entities.Role;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author HP
 */
public class DAORole extends DBConnection {

    // Add a new role
    public int addRole(Role role) {
        int result = 0;
        String sql = "INSERT INTO Roles (roleName) VALUES (?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, role.getRoleName());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Get all roles
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM Roles";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int roleId = rs.getInt("roleId");
                String roleName = rs.getString("roleName");
                roles.add(new Role(roleId, roleName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    // Get role by ID
    public Role getRoleById(int roleId) {
        Role role = null;
        String sql = "SELECT * FROM Roles WHERE roleId = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, roleId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String roleName = rs.getString("roleName");
                role = new Role(roleId, roleName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    // Update a role
    public int updateRole(Role role) {
        int result = 0;
        String sql = "UPDATE Roles SET roleName = ? WHERE roleId = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.setInt(2, role.getRoleId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Delete a role
    public int deleteRole(int roleId) {
        int result = 0;
        String sql = "DELETE FROM Roles WHERE roleId = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, roleId);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Test the DAORole
    public static void main(String[] args) {
        DAORole daoRole = new DAORole();

        // Add a new role
        //Role adminRole = new Role(0, "Admin");
        //daoRole.addRole(adminRole);

        // Get all roles
        //List<Role> roles = daoRole.getAllRoles();
        //for (Role role : roles) {
        //    System.out.println(role);
        //}

        // Update a role
        //Role roleToUpdate = new Role(1, "Super Admin");
        //daoRole.updateRole(roleToUpdate);

        // Get role by ID
        Role retrievedRole = daoRole.getRoleById(1);
        System.out.println("Retrieved Role: " + retrievedRole);

        // Delete a role
        //daoRole.deleteRole(1);
    }
}
