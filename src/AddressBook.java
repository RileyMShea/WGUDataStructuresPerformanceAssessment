public class AddressBook {
    public static void main(String[] args) {
        HashTable<String> test = new HashTable();

//        String firstName ="Bob";
//        String lastName = "Smith";
//       String firstName ="Jane";
//        String lastName = "Williams";
//       String firstName ="Mohammed";
//        String lastName = "al-Salam";
       String firstName ="Billy";
        String lastName = "Kidd";

        String fullName = firstName.concat(lastName);
        System.out.println(fullName.toUpperCase());
        int nameHash = fullName.toUpperCase().hashCode();
        int compressedHash = nameHash % fullName.length();
        if(compressedHash<0)
            compressedHash = compressedHash + fullName.length();
        System.out.println(nameHash);
        System.out.println(compressedHash);

    }
}
