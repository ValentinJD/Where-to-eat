package ru.wheretoeat.web;

import ru.wheretoeat.HasId;

public interface HasIdAndEmail extends HasId {
    String getEmail();
}
