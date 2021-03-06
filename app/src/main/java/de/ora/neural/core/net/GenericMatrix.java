package de.ora.neural.core.net;

import java.util.Arrays;
import java.util.Objects;

public class GenericMatrix<VALUE_T> {
    public Object[][] data;
    int rows;
    int columns;

    private GenericMatrix() {
    }

    public GenericMatrix(Object[][] otherData) {
        this.rows = otherData.length;
        this.columns = otherData[0].length;
        this.data = createDataBuffer(this.rows, this.columns);
        for (int row = 0; row < otherData.length; row++) {
            for (int cell = 0; cell < otherData[row].length; cell++) {
                this.data[row][cell] = otherData[row][cell];
            }
        }
    }

    public GenericMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = createDataBuffer(this.rows, this.columns);
    }

    public GenericMatrix copy() {
        return new GenericMatrix(data);
    }

    protected Object[][] createDataBuffer(int rows, int columns) {
        return new Object[rows][columns];
    }

    public GenericMatrix initWith(VALUE_T value) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                data[i][j] = value;
            }
        }
        return this;
    }


    public Object[] getColumn(int column) {
        Object[] result = new Object[rows];
        for (int i = 0; i < rows; i++) {
            result[i] = (VALUE_T) data[i][column];
        }

        return result;
    }

    public VALUE_T getRawCell(int row, int column) {
        return (VALUE_T) data[row][column];
    }

    public int getRowCount() {
        return rows;
    }

    public int getColumnCount() {
        return columns;
    }

    private boolean dimensionsMatch(GenericMatrix input) {
        return rows == input.rows && columns == input.columns;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sb.append(data[i][j]).append(" ");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    private void assertDimensionsMatch(GenericMatrix input) {
        if (!dimensionsMatch(input)) {
            throw new IllegalArgumentException("Dimensions do not match: expected rows " + rows + " found " + input.rows + ", expected columns " + columns + " found " + input.columns);
        }
    }

    public VALUE_T set(int row, int column, VALUE_T value) {
        VALUE_T oldValue = (VALUE_T) this.data[row][column];
        this.data[row][column] = value;
        return oldValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericMatrix matrix = (GenericMatrix) o;
        if (getRowCount() != matrix.getRowCount() ||
                getColumnCount() != matrix.getColumnCount()) {
            return false;
        }

        for (int row = 0; row < data.length; row++) {
            Object[] myRow = data[row];
            Object[] otherRow = ((GenericMatrix) o).data[row];
            for (int col = 0; col < myRow.length; col++) {
                if (!myRow[col].equals(otherRow[col])) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getRowCount(), getColumnCount());
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    public Object[] getRow(int row) {
        return data[row];
    }

    public Object[][] getRows() {
        return this.data;
    }
}
