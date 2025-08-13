package ru.ex.managerms.entity;

import java.util.UUID;

public record Product(UUID id, String title, String details) {
}
