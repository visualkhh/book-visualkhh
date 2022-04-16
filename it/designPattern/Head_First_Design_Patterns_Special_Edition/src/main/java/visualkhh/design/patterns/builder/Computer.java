package visualkhh.design.patterns.builder;

public class Computer {
    private String cpu;
    private String hdd;
    private Integer hddVolume;
    private String ssd;
    private Integer ssdVolume;
    private String monitor;
    private Boolean bluetoothDongle;

    private Computer(builder builder) {
        this.cpu = builder.cpu;
        this.hdd = builder.hdd;
        this.hddVolume = builder.hddVolume;
        this.ssd = builder.ssd;
        this.ssdVolume = builder.ssdVolume;
        this.monitor = builder.monitor;
        this.bluetoothDongle = builder.bluetoothDongle;
    }

    public static class builder { // Essential value
        private final String cpu;
        private final String hdd;
        private final Integer hddVolume; // Selective valu
        private String ssd;
        private Integer ssdVolume;
        private String monitor;
        private Boolean bluetoothDongle;

        public builder(String cpu, String hdd, Integer hddVolume) {
            this.cpu = cpu;
            this.hdd = hdd;
            this.hddVolume = hddVolume;
        }

        public builder ssd(String ssd, Integer ssdVolume) {
            this.ssd = ssd;
            this.ssdVolume = ssdVolume;
            return this;
        }

        public builder monitor(String monitor) {
            this.monitor = monitor;
            return this;
        }

        public builder bluetoothDongle(Boolean bluetoothDongle) {
            this.bluetoothDongle = bluetoothDongle;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
