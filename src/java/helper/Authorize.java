/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import entities.User;

/**
 *
 * @author HP
 */
public class Authorize {
    private static final String[] PUBLIC_ENDPOINTS = {"/HomeForward", "/CartController"};
    private static final String[] CUSTOMER_ENDPOINTS = {"/HomeForward", "/CartController", "/OrderDetails"};
    private static final String[] ADMIN_ENDPOINTS = {"/", "/"};
    private static final String[] SALES_ENDPOINTS = {"/", "/"};
    private static final String[] MARKETING_ENDPOINTS = {"/", "/"};

    public static boolean isAccepted(User user, String link) {
        if (user == null) {
            return isLinkAllowed(link, PUBLIC_ENDPOINTS);
        }
        switch (user.getRoleId()) {
            case 1: // Admin
                return isLinkAllowed(link, ADMIN_ENDPOINTS);
            case 2: // Customer
                return isLinkAllowed(link, CUSTOMER_ENDPOINTS);
            case 3: // Sales
                return isLinkAllowed(link, SALES_ENDPOINTS);
            case 4: // Marketing
                return isLinkAllowed(link, MARKETING_ENDPOINTS);
            default: // Public access
                return isLinkAllowed(link, PUBLIC_ENDPOINTS);
        }
    }

    private static boolean isLinkAllowed(String link, String[] endpoints) {
        for (String endpoint : endpoints) {
            if (endpoint.equals(link)) {
                return true;
            }
        }
        return false;
    }
}

