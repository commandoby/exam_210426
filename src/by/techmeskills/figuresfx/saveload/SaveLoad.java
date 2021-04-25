package by.techmeskills.figuresfx.saveload;

import by.techmeskills.figuresfx.exceptions.SaveLoadException;
import by.techmeskills.figuresfx.figures.Figure;

import java.util.ArrayDeque;

public class SaveLoad {
    private ArrayDeque<Figure> figureSaveList;

    public SaveLoad(ArrayDeque<Figure> figureList) throws SaveLoadException {
        figureSaveList = new ArrayDeque<>();
        save(figureList);
    }

    //запись фигур
    public void save(ArrayDeque<Figure> figureList) throws SaveLoadException {
        figureSaveList = new ArrayDeque<>();
        if (!figureList.isEmpty()) {
            figureSaveList.addAll(figureList);
        } else {
            throw new SaveLoadException("Trying to keep an empty list.");
        }
    }

    //чтение фигур
    public ArrayDeque<Figure> load() throws SaveLoadException {
        if (!figureSaveList.isEmpty()) {
            return figureSaveList;
        } else {
            throw new SaveLoadException("An attempt was made to load an empty list.");
        }
    }
}
