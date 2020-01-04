package com.j97.app.data.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = "material_models")
public class MaterialModel {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "_id")
  private int id;
  @ColumnInfo(name = "material_id")
  private int materialId;
  @ColumnInfo(name = "a")
  private double A;
  @ColumnInfo(name = "e")
  private double E;
  @ColumnInfo(name = "i")
  private double I;
  @ColumnInfo(name = "type")
  private String type;
  @ColumnInfo(name = "created_at")
  private Date createdAt;

  public MaterialModel() {
  }

  public MaterialModel(int materialId, String type, double e, double a, double i) {
    this.materialId = materialId;
    this.type = type;
    this.A = a;
    this.E = e;
    this.I = i;
    this.createdAt = new Date();
  }

  public int getId() {
    return id;
  }

  public double getA() {
    return A;
  }

  public void setA(double a) {
    A = a;
  }

  public double getE() {
    return E;
  }

  public void setE(double e) {
    E = e;
  }

  public double getI() {
    return I;
  }

  public void setI(double i) {
    I = i;
  }

  public int getMaterialId() {
    return materialId;
  }

  public void setMaterialId(int materialId) {
    this.materialId = materialId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MaterialModel that = (MaterialModel) o;
    return id == that.id &&
        materialId == that.materialId &&
        Double.compare(that.A, A) == 0 &&
        Double.compare(that.E, E) == 0 &&
        Double.compare(that.I, I) == 0 &&
        Objects.equals(type, that.type) &&
        Objects.equals(createdAt, that.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, materialId, A, E, I, type, createdAt);
  }

  @Override
  public String toString() {
    return "MaterialModel{" +
        "id=" + id +
        ", materialId=" + materialId +
        ", A=" + A +
        ", E=" + E +
        ", I=" + I +
        ", type='" + type + '\'' +
        ", createdAt=" + createdAt +
        '}';
  }
}