package com.jspotify.dao;

import com.jspotify.model.User;
import com.jspotify.model.UserRole;

import java.time.LocalDateTime;
import java.util.List;

public class UserDAOTest {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();

        try {

            // ==================================================
            // 1. CREATE
            // ==================================================

            System.out.println(
                    "\n========== CREATE =========="
            );


            User user = new User();

            user.setUsername(
                    "testuser_" +
                    System.currentTimeMillis()
            );

            user.setEmail(
                    "test_" +
                    System.currentTimeMillis() +
                    "@example.com"
            );

            user.setPasswordHash(
                    "temporary-hash"
            );

            user.setProfileImage(null);

            user.setRole(
                    UserRole.USER
            );

            user.setCreatedAt(
                    LocalDateTime.now()
            );


            User createdUser =
                    userDAO.create(user);


            System.out.println(
                    "Created user:"
            );

            System.out.println(
                    createdUser
            );


            // ==================================================
            // 2. FIND BY ID
            // ==================================================

            System.out.println(
                    "\n========== FIND BY ID =========="
            );


            User foundById =
                    userDAO.findById(
                            createdUser.getId()
                    );


            System.out.println(
                    foundById
            );


            // ==================================================
            // 3. FIND BY USERNAME
            // ==================================================

            System.out.println(
                    "\n========== FIND BY USERNAME =========="
            );


            User foundByUsername =
                    userDAO.findByUsername(
                            createdUser.getUsername()
                    );


            System.out.println(
                    foundByUsername
            );


            // ==================================================
            // 4. FIND BY EMAIL
            // ==================================================

            System.out.println(
                    "\n========== FIND BY EMAIL =========="
            );


            User foundByEmail =
                    userDAO.findByEmail(
                            createdUser.getEmail()
                    );


            System.out.println(
                    foundByEmail
            );


            // ==================================================
            // 5. FIND ALL
            // ==================================================

            System.out.println(
                    "\n========== FIND ALL =========="
            );


            List<User> users =
                    userDAO.findAll();


            for (User currentUser : users) {

                System.out.println(
                        currentUser
                );
            }


            // ==================================================
            // 6. UPDATE
            // ==================================================

            System.out.println(
                    "\n========== UPDATE =========="
            );


            createdUser.setUsername(
                    createdUser.getUsername() +
                    "_updated"
            );


            boolean updated =
                    userDAO.update(
                            createdUser
                    );


            System.out.println(
                    "Updated: " + updated
            );


            User updatedUser =
                    userDAO.findById(
                            createdUser.getId()
                    );


            System.out.println(
                    updatedUser
            );


            // ==================================================
            // 7. DELETE
            // ==================================================

            System.out.println(
                    "\n========== DELETE =========="
            );


            boolean deleted =
                    userDAO.delete(
                            createdUser.getId()
                    );


            System.out.println(
                    "Deleted: " + deleted
            );


            // ==================================================
            // 8. VERIFY DELETE
            // ==================================================

            System.out.println(
                    "\n========== VERIFY DELETE =========="
            );


            User deletedUser =
                    userDAO.findById(
                            createdUser.getId()
                    );


            if (deletedUser == null) {

                System.out.println(
                        "✅ User successfully deleted."
                );

            } else {

                System.out.println(
                        "❌ User still exists."
                );
            }


            System.out.println(
                    "\n✅ USER DAO CRUD TEST COMPLETED!"
            );


        } catch (Exception e) {

            System.err.println(
                    "\n❌ DAO TEST FAILED!"
            );

            e.printStackTrace();
        }
    }
}