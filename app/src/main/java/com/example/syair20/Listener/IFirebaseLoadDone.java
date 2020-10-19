package com.example.syair20.Listener;

import com.example.syair20.Model.Materi;

import java.util.List;

public interface IFirebaseLoadDone {
    void onFirebaseLoadSuccess(List<Materi>materiList);
    void onFirebaseLoadFailed(String message);
}
