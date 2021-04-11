CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE SERVICE_FEATURE
(
    id SERIAL NOT NULL,
    uuid uuid default uuid_generate_v4(),
    type varchar NOT NULL,
    duration int NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (uuid),
    UNIQUE (type, duration)
);

CREATE TABLE APPLIANCE
(
    id SERIAL NOT NULL,
    uuid uuid default uuid_generate_v4(),
    name varchar NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (uuid)
);

CREATE TABLE APPLIANCE_SERVICE_FEATURE
(
    service_feature_id int REFERENCES SERVICE_FEATURE(id),
    appliance_id int REFERENCES APPLIANCE(id),
    CONSTRAINT appliances_service_feature_id PRIMARY KEY (service_feature_id, appliance_id)
);

CREATE TABLE HOUSEBLOCK
(
    id SERIAL NOT NULL,
    uuid uuid default uuid_generate_v4(),
    name varchar NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (uuid),
    UNIQUE (name)
);

CREATE TABLE HOUSEHOLD
(
    id SERIAL NOT NULL,
    uuid uuid default uuid_generate_v4(),
    name varchar NOT NULL,
    household_block_id int REFERENCES HOUSEBLOCK(id),
    PRIMARY KEY (id),
    UNIQUE (uuid)
);

CREATE TABLE LAUNDRYROOM
(
    id SERIAL NOT NULL,
    uuid uuid default uuid_generate_v4(),
    name varchar NOT NULL,
    household_block_id int REFERENCES HOUSEBLOCK(id),
    PRIMARY KEY (id),
    UNIQUE (uuid),
    UNIQUE (name, household_block_id)
);

CREATE TABLE LAUNDRYROOM_APPLIANCES
(
    laundry_room_id int REFERENCES LAUNDRYROOM(id),
    appliance_id int REFERENCES APPLIANCE(id),
    CONSTRAINT laundryroom_appliances_id PRIMARY KEY (laundry_room_id, appliance_id)
);

CREATE TABLE BOOKING
(
    id SERIAL NOT NULL,
    uuid uuid default uuid_generate_v4(),
    household_id int REFERENCES HOUSEHOLD(id),
    laundry_room_id int REFERENCES LAUNDRYROOM(id),
    start_time timestamp with time zone  NOT NULL,
    end_time timestamp with time zone  NOT NULL,
    status varchar NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (uuid),
    UNIQUE (laundry_room_id, start_time, end_time)
);