package com.j97.app.data.local;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = "node_models")
public class NodeModel implements Parcelable {
  public static final Creator<NodeModel> CREATOR = new Creator<NodeModel>() {
    @Override
    public NodeModel createFromParcel(Parcel in) {
      return new NodeModel(in);
    }

    @Override
    public NodeModel[] newArray(int size) {
      return new NodeModel[size];
    }
  };
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "_id")
  private int id;
  @ColumnInfo(name = "node_id")
  private int nodeId;
  @ColumnInfo(name = "Coor_X")
  private double CoorX;
  @ColumnInfo(name = "Coor_Y")
  private double CoorY;
  @ColumnInfo(name = "created_at")
  private Date createdAt;

  public NodeModel() {
  }

  public NodeModel(int nodeId, double coorX, double coorY) {
    this.nodeId = nodeId;
    CoorX = coorX;
    CoorY = coorY;
    this.createdAt = new Date();
  }

  protected NodeModel(Parcel in) {
    id = in.readInt();
    nodeId = in.readInt();
    CoorX = in.readDouble();
    CoorY = in.readDouble();
    createdAt = new Date(in.readLong());
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getNodeId() {
    return nodeId;
  }

  public void setNodeId(int nodeId) {
    this.nodeId = nodeId;
  }

  public double getCoorX() {
    return CoorX;
  }

  public void setCoorX(double coorX) {
    CoorX = coorX;
  }

  public double getCoorY() {
    return CoorY;
  }

  public void setCoorY(double coorY) {
    CoorY = coorY;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeInt(nodeId);
    dest.writeDouble(CoorX);
    dest.writeDouble(CoorY);
    dest.writeLong(createdAt.getTime());
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NodeModel that = (NodeModel) o;
    return id == that.id &&
        nodeId == that.nodeId &&
        Double.compare(that.CoorX, CoorX) == 0 &&
        Double.compare(that.CoorY, CoorY) == 0 &&
        Objects.equals(createdAt, that.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nodeId, CoorX, CoorY, createdAt);
  }

  @Override
  public String toString() {
    return "MaterialModel{" +
        "id=" + id +
        ", nodeId=" + nodeId +
        ", Coor X=" + CoorX +
        ", Coor Y=" + CoorY + '\'' +
        ", createdAt=" + createdAt +
        '}';
  }
}