package org.leftbrained.roombinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import org.leftbrained.roombinding.databinding.ActivityMainBinding;
import org.leftbrained.roombinding.db.AppDatabase;
import org.leftbrained.roombinding.model.User;
import org.leftbrained.roombinding.model.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private UserDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries().build();
        dao = database.userDao();
        binding.addBtn.setOnClickListener(view -> {
            String login = String.valueOf(binding.inputLogin.getText());
            String name = String.valueOf(binding.inputName.getText());
            dao.insert(new User(login, name));
        });
        binding.clearBtn.setOnClickListener(view -> dao.deleteAll());
        binding.readBtn.setOnClickListener(view -> {
            StringBuilder sb = new StringBuilder();
            for (User user : dao.getAll()) {
                sb.append(user.login).append(" ").append(user.name).append("\n");
            }
            binding.logText.setText(sb.toString());
        });
        binding.loginBtn.setOnClickListener(view -> loginUser());
    }

    private void loginUser() {
        List<User> users = dao.getAll();
        if (users.isEmpty()) {
            return;
        }
        if (binding.inputName.getText().toString().isEmpty() || binding.inputLogin.getText().toString().isEmpty()) {
            return;
        }
        String login = binding.inputLogin.getText().toString();
        String name = binding.inputName.getText().toString();
        for (User user : users) {
            if (user.name.equals(login) && user.login.equals(name)) {
                user.loginCount++;
                dao.update(user);
            }
        }
        User userMax = userMaxCount();
        binding.logText.setText(userMax.name + " " + userMax.login + " " + userMax.loginCount);
    }

    private User userMaxCount() {
        List<User> users = dao.getAll();
        User userMax = users.get(0);
        for (User user : users) {
            if (user.loginCount > userMax.loginCount) {
                userMax = user;
            }
        }
        return userMax;
    }
}