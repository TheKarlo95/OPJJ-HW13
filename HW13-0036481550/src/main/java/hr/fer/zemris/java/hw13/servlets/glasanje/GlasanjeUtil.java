package hr.fer.zemris.java.hw13.servlets.glasanje;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * {@code GlasanjeUtil} is a utility class that works with files that contains
 * the information about nominees and vote count.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class GlasanjeUtil {

    /**
     * Read a list of nominees from a specified path
     * 
     * @param path
     *            path to file with list of nominees
     * @return a list of nominees
     * @throws NullPointerException
     *             if parameter {@code path} is a {@code null} reference
     */
    static List<Nominee> readNominees(String path) {
        Objects.requireNonNull(path, "You cannot provide null reference as a path to input file!");
        return readNominees(Paths.get(path));
    }

    /**
     * Read a list of nominees from a specified path
     * 
     * @param path
     *            path to file with list of nominees
     * @return a list of nominees
     * @throws NullPointerException
     *             if parameter {@code path} is a {@code null} reference
     */
    static List<Nominee> readNominees(Path path) {
        Objects.requireNonNull(path, "You cannot provide null reference as a path to input file!");
        List<Nominee> nominees = new ArrayList<>();

        if (Files.isReadable(path)) {
            try {
                Files.readAllLines(path).forEach(line -> {
                    String[] splited = line.split("\t");
                    try {
                        nominees.add(new Nominee(Integer.parseInt(splited[0]), splited[1], splited[2]));
                    } catch (NumberFormatException ignore) {
                    }
                });
            } catch (IOException ignore) {
            }
        }

        return nominees;
    }

    /**
     * Read a list of nominees and their vote count from a specified path
     * 
     * @param nomineesPath
     *            path to file with list of nominees
     * @param path
     *            path to file with vote counts
     * @return a list of nominees
     * @throws NullPointerException
     *             if parameter {@code nomineesPath} or {@code path} is a
     *             {@code null} reference
     */
    static List<Nominee> readNomineeVotes(String nomineesPath, String path) {
        Objects.requireNonNull(nomineesPath, "You cannot provide null reference as a path to nominee list file!");
        Objects.requireNonNull(path, "You cannot provide null reference as a path to nominee vote count file!");

        return readNomineeVotes(Paths.get(nomineesPath), Paths.get(path));
    }

    /**
     * Read a list of nominees and their vote count from a specified path
     * 
     * @param nomineesPath
     *            path to file with list of nominees
     * @param path
     *            path to file with vote counts
     * @return a list of nominees
     * @throws NullPointerException
     *             if parameter {@code nomineesPath} or {@code path} is a
     *             {@code null} reference
     */
    static List<Nominee> readNomineeVotes(Path nomineesPath, Path path) {
        Objects.requireNonNull(nomineesPath, "You cannot provide null reference as a path to nominee list file!");
        Objects.requireNonNull(path, "You cannot provide null reference as a path to nominee vote count file!");

        List<Nominee> nominees = readNominees(nomineesPath);

        if (Files.isReadable(path)) {
            try {
                Files.readAllLines(path).forEach(line -> {
                    String[] splited = line.split("\t");

                    int id = Integer.parseInt(splited[0]);
                    int numOfVotes = Integer.parseInt(splited[1]);

                    nominees.get(id - 1).setNumOfVotes(numOfVotes);
                });
            } catch (IOException ignore) {
            }
        }

        return nominees;
    }

    /**
     * Writes nominees IDs and their vote count to a file on specified path.
     * 
     * @param path
     *            path to file with vote counts
     * @param nominees
     *            a list of nominees
     * @throws NullPointerException
     *             if if parameter {@code path} or {@code nominees} is a
     *             {@code null} reference
     */
    static void writeVotes(String path, List<Nominee> nominees) {
        Objects.requireNonNull(path, "You cannot provide null reference as a path to nominee vote count file!");
        Objects.requireNonNull(nominees, "You cannot provide null reference as a list of nominees!");

        writeVotes(Paths.get(path), nominees);
    }

    /**
     * Writes nominees IDs and their vote count to a file on specified path.
     * 
     * @param path
     *            path to file with vote counts
     * @param nominees
     *            a list of nominees
     * @throws NullPointerException
     *             if if parameter {@code path} or {@code nominees} is a
     *             {@code null} reference
     */
    static void writeVotes(Path path, List<Nominee> nominees) {
        Objects.requireNonNull(path, "You cannot provide null reference as a path to nominee vote count file!");
        Objects.requireNonNull(nominees, "You cannot provide null reference as a list of nominees!");

        String str = nominees.stream()
                .map(v -> v.getId() + "\t" + v.getNumOfVotes())
                .sorted()
                .collect(Collectors.joining(System.getProperty("line.separator")));

        try (OutputStream os = Files.newOutputStream(path)) {
            synchronized (os) {
                os.write(str.getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException ignore) {
        }
    }

    /**
     * Returns the {@code value} or {@code defaultValue} if {@code value} is
     * {@code null} reference.
     * 
     * @param value
     *            the value
     * @param defaultValue
     *            the default value
     * @return the {@code value} or {@code defaultValue} if {@code value} is
     *         {@code null} reference
     */
    static <T> T getValueOrDefault(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    /**
     * 
     * @author Karlo Vrbić
     * @version 1.0
     */
    public static class Nominee implements Serializable {

        /** Serial version UID */
        private static final long serialVersionUID = 1268512721343012498L;

        /** ID of the nominated artist/group. */
        private int id;
        /** Name of the nominated artist/group. */
        private String name;
        /** Link to the nominated song. */
        private String link;
        /** Number of votes received from users. */
        private int numOfVotes;

        /**
         * COnstructs a new {@code Nominee} with specified ID, name and link to
         * nominated song.
         * 
         * @param id
         *            ID of the nominated artist/group
         * @param name
         *            name of the nominated artist/group
         * @param link
         *            link to the nominated song
         */
        public Nominee(int id, String name, String link) {
            if (id < 1)
                throw new IllegalArgumentException("Nominee ID cannot be less than 1 and you provided " + id + "!");

            this.id = id;
            this.name = Objects.requireNonNull(name, "You cannot pass a null reference as a name of a nominee!");
            this.link = Objects.requireNonNull(link, "You cannot pass a null reference as a link of a nominee!");
            this.numOfVotes = 0;
        }

        /**
         * Returns the ID of the nominated artist/group.
         * 
         * @return the ID of the nominated artist/group
         */
        public int getId() {
            return id;
        }

        /**
         * Returns the link on which user can vote for this nominee.
         * 
         * @return the link on which user can vote for this nominee.
         */
        public String getVoteLink() {
            return "glasanje-glasaj?id=" + id;
        }

        /**
         * Returns the name of the nominated artist/group
         * 
         * @return the name of the nominated artist/group
         */
        public String getName() {
            return name;
        }

        /**
         * Returns the link to the nominated song
         * 
         * @return the link to the nominated song
         */
        public String getLink() {
            return link;
        }

        /**
         * Returns the number of votes received from users
         * 
         * @return number of votes received from users
         */
        public int getNumOfVotes() {
            return numOfVotes;
        }

        /**
         * Sets the new number of votes received.
         * 
         * @param numOfVotes
         *            the new number of votes
         */
        public void setNumOfVotes(int numOfVotes) {
            if (numOfVotes < 0)
                throw new IllegalArgumentException("You cannot set number of votes to negative number!");

            this.numOfVotes = numOfVotes;
        }

        /**
         * Increments the number of votes received.
         */
        public void incrementNumOfVotes() {
            this.numOfVotes++;
        }
    }

}
